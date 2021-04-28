import React, { useState } from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { signup, checkEmailAvailability, checkUsernameAvailability } from '../../util/APIUtils';

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
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default function SignUp() {
  const classes = useStyles();
  const [name, setName] = useState('');
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  let usernameAvailable = true;
  let emailAvailable = true;

  function handleNameChange(event) {
    setName(event.target.value);
  }

  function handleUsernameChange(event) {
    setUsername(event.target.value);
    checkUsernameAvailability(username)
      .then(response => {
        usernameAvailable = response.available;
      }).catch(error => {
        Promise.reject(error);
      })
  }

  function handleEmailChange(event) {
    setEmail(event.target.value);
    checkEmailAvailability(email)
      .then(response => {
        emailAvailable = response.available;
      }).catch(error => {
        Promise.reject(error);
      })
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  function handleSubmit(event) {
    event.preventDefault();
    signup({ name, username, email, password })
      .then(response => {
        window.location.replace(process.env.REACT_APP_SIGNIN || "http://localhost:3000/signin");
        // TODO: display success message using Snackbars
      }).catch(error => {
        // TODO: display error message using Snackbars
      })
  }

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        <form className={classes.form} noValidate onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                onChange={handleNameChange}
                value={name}
                autoComplete="fname"
                name="fullName"
                variant="outlined"
                required
                fullWidth
                id="fullName"
                label="Full Name"
                helperText="Name must be between 4 and 40 characters."
                autoFocus
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                error={!usernameAvailable}
                onChange={handleUsernameChange}
                value={username}
                variant="outlined"
                required
                fullWidth
                id="username"
                label="Username"
                name="username"
                autoComplete="username"
                helperText="Username must be between 3 and 15 characters."
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                error={!emailAvailable}
                onChange={handleEmailChange}
                value={email}
                variant="outlined"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                helperText="Email cannot be more than 40 characters."
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                onChange={handlePasswordChange}
                value={password}
                variant="outlined"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                helperText="Password must be between 6 and 20 characters."
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign Up
          </Button>
          <Grid container justify="flex-end">
            <Grid item>
              <Link href="/signin" variant="body2">
                Already have an account? Sign in
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
    </Container>
  );
}