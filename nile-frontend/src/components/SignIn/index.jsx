import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import { useSnackbar, withSnackbar } from 'notistack';
import React, { useState } from 'react';
import { ACCESS_TOKEN } from '../../constants';
import { useStateValue } from '../../StateProvider';
import { getCurrentUser, login } from '../../util/APIUtils';

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

const SignIn = (props) => {
  const classes = useStyles();
  const ref = React.createRef();
  const { enqueueSnackbar } = useSnackbar();
  const [usernameOrEmail, setUsernameOrEmail] = useState('');
  const [password, setPassword] = useState('');
  const [{ state }, dispatch] = useStateValue();

  function handleUsernameOrEmailChange(event) {
    setUsernameOrEmail(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  function handleSubmit(event) {
    event.preventDefault();
    login({ usernameOrEmail, password })
      .then((response) => {
        enqueueSnackbar('Login was successful!', {
          variant: 'success',
          anchorOrigin: {
            vertical: 'bottom', horizontal: 'center',
          },
        });
        sessionStorage.setItem(ACCESS_TOKEN, response.accessToken);
        getCurrentUser()
          .then(response => {
            enqueueSnackbar('User authenticated successfully!', {
              variant: 'success',
              anchorOrigin: {
                vertical: 'bottom', horizontal: 'center',
              },
            });
            dispatch({
              type: 'USER_SIGN_IN',
              currentUser: response,
            });
            props.history.push('/');
          }).catch(error => {
            enqueueSnackbar('User authentication failed!', {
              variant: 'error',
              anchorOrigin: {
                vertical: 'bottom', horizontal: 'center',
              },
            });
          });
      }).catch((error) => {
        enqueueSnackbar('User login failed!', {
          variant: 'error',
          anchorOrigin: {
            vertical: 'bottom', horizontal: 'center',
          },
        });
      });
  }

  return (
    <Container component="main" maxWidth="xs" ref={ref}>
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
        <form className={classes.form} noValidate onSubmit={handleSubmit}>
          <TextField
            onChange={handleUsernameOrEmailChange}
            value={usernameOrEmail}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="usernameOrEmail"
            label="Username or Email"
            name="usernameOrEmail"
            autoComplete="email"
            autoFocus
          />
          <TextField
            onChange={handlePasswordChange}
            value={password}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign In
          </Button>
          <Grid container>
            <Grid item>
              <Link href="/signup" variant="body2">
                Don't have an account? Sign Up
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
    </Container>
  );
};

export default withSnackbar(SignIn);
