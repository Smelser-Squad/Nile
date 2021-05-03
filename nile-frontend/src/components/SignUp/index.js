import React, { Component } from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { withSnackbar } from 'notistack';
import { signup, checkEmailAvailability, checkUsernameAvailability } from '../../util/APIUtils';
import {
  NAME_MIN_LENGTH, NAME_MAX_LENGTH,
  USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH,
  EMAIL_MAX_LENGTH,
  PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH
} from '../../constants';

const styles = (theme) => ({
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
});

class SignUp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: {
        value: ''
      },
      username: {
        value: ''
      },
      email: {
        value: ''
      },
      password: {
        value: ''
      },
    }
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.validateUsernameAvailability = this.validateUsernameAvailability.bind(this);
    this.validateEmailAvailability = this.validateEmailAvailability.bind(this);
    this.isFormInvalid = this.isFormInvalid.bind(this);
  }

  handleInputChange(event, validationFn) {
    const target = event.target;
    const inputName = target.name;
    const inputValue = target.value;

    this.setState({
      [inputName]: {
        value: inputValue,
        ...validationFn(inputValue)
      }
    });
  }

  handleSubmit(event) {
    event.preventDefault();
    const signupRequest = {
      name: this.state.name.value,
      email: this.state.email.value,
      username: this.state.username.value,
      password: this.state.password.value
    };
    signup(signupRequest)
      .then(response => {
        this.props.enqueueSnackbar('User registered successfully!', {
          variant: 'success', anchorOrigin: { vertical: 'bottom', horizontal: 'center' }
        });
        this.props.history.push("/signin");
      }).catch(error => {
        this.props.enqueueSnackbar('Something went wrong!', {
          variant: 'error', anchorOrigin: { vertical: 'bottom', horizontal: 'center ' }
        });
      })
  }

  isFormInvalid() {
    return !(this.state.name.validateStatus === 'success' &&
      this.state.username.validateStatus === 'success' &&
      this.state.email.validateStatus === 'success' &&
      this.state.password.validateStatus === 'success'
    );
  }

  render() {
    const { classes } = this.props;
    return (
      <Container component="main" maxWidth="xs">
        <div className={classes.paper}>
          <Avatar className={classes.avatar}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign up
        </Typography>
          <form
            className={classes.form}
            noValidate
            onSubmit={this.handleSubmit}
          >
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  onChange={event => this.handleInputChange(event, this.validateName)}
                  value={this.state.name.value}
                  error={this.state.name.validateStatus === 'error'}
                  helperText={this.state.name.errorMsg}
                  autoComplete="fname"
                  name="name"
                  variant="outlined"
                  required
                  fullWidth
                  id="name"
                  label="Full Name"
                  placeholder="Your full name"
                  autoFocus
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  onChange={event => this.handleInputChange(event, this.validateUsername)}
                  onBlur={this.validateUsernameAvailability}
                  value={this.state.username.value}
                  error={this.state.username.validateStatus === 'error'}
                  helperText={this.state.username.errorMsg}
                  variant="outlined"
                  required
                  fullWidth
                  id="username"
                  label="Username"
                  placeholder="A unique username"
                  name="username"
                  autoComplete="username"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  onChange={event => this.handleInputChange(event, this.validateEmail)}
                  onBlur={this.validateEmailAvailability}
                  value={this.state.email.value}
                  error={this.state.email.validateStatus === 'error'}
                  helperText={this.state.email.errorMsg}
                  variant="outlined"
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  placeholder="Your email"
                  autoComplete="email"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  onChange={event => this.handleInputChange(event, this.validatePassword)}
                  value={this.state.password.value}
                  error={this.state.password.validateStatus === 'error'}
                  helperText={this.state.password.errorMsg}
                  variant="outlined"
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  placeholder="A password between 6 to 20 characters"
                  autoComplete="current-password"
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
              className={classes.submit}
              disabled={this.isFormInvalid()}
              onClick={this.handleClick}
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

  // Validation functions

  validateName(name) {
    if (name.length < NAME_MIN_LENGTH) {
      return {
        validateStatus: 'error',
        errorMsg: `Name is too short (Minimum ${NAME_MIN_LENGTH} characters needed.)`
      }
    } else if (name.length > NAME_MAX_LENGTH) {
      return {
        validateStatus: 'error',
        errorMsg: `Name is too long (Maximum ${NAME_MAX_LENGTH} characters allowed.)`
      }
    } else {
      return {
        validateStatus: 'success',
        errorMsg: null,
      };
    }
  }

  validateEmail(email) {
    if (!email) {
      return {
        validateStatus: 'error',
        errorMsg: 'Email may not be empty'
      }
    }

    const EMAIL_REGEX = RegExp('[^@ ]+@[^@ ]+\\.[^@ ]+');
    if (!EMAIL_REGEX.test(email)) {
      return {
        validateStatus: 'error',
        errorMsg: 'Email not valid'
      }
    }

    if (email.length > EMAIL_MAX_LENGTH) {
      return {
        validateStatus: 'error',
        errorMsg: `Email is too long (Maximum ${EMAIL_MAX_LENGTH} characters allowed)`
      }
    }

    return {
      validateStatus: null,
      errorMsg: null
    }
  }

  validateUsername(username) {
    if (username.length < USERNAME_MIN_LENGTH) {
      return {
        validateStatus: 'error',
        errorMsg: `Username is too short (Minimum ${USERNAME_MIN_LENGTH} characters needed.)`
      }
    } else if (username.length > USERNAME_MAX_LENGTH) {
      return {
        validateStatus: 'error',
        errorMsg: `Username is too long (Maximum ${USERNAME_MAX_LENGTH} characters allowed.)`
      }
    } else {
      return {
        validateStatus: null,
        errorMsg: null
      }
    }
  }

  validateUsernameAvailability() {
    // First check for client side errors in username
    const usernameValue = this.state.username.value;
    const usernameValidation = this.validateUsername(usernameValue);

    if (usernameValidation.validateStatus === 'error') {
      this.setState({
        username: {
          value: usernameValue,
          ...usernameValidation
        }
      });
      return;
    }

    this.setState({
      username: {
        value: usernameValue,
        validateStatus: 'pending',
        errorMsg: null
      }
    });

    checkUsernameAvailability(usernameValue)
      .then(response => {
        if (response.available) {
          this.setState({
            username: {
              value: usernameValue,
              validateStatus: 'success',
              errorMsg: null
            }
          });
        } else {
          this.setState({
            username: {
              value: usernameValue,
              validateStatus: 'error',
              errorMsg: 'This username is already taken'
            }
          });
        }
      }).catch(error => {
        // Marking validateStatus as success, Form will be recchecked at server
        this.setState({
          username: {
            value: usernameValue,
            validateStatus: 'success',
            errorMsg: null
          }
        });
      });
  }

  validateEmailAvailability() {
    // First check for client side errors in email
    const emailValue = this.state.email.value;
    const emailValidation = this.validateEmail(emailValue);

    if (emailValidation.validateStatus === 'error') {
      this.setState({
        email: {
          value: emailValue,
          ...emailValidation
        }
      });
      return;
    }

    this.setState({
      email: {
        value: emailValue,
        validateStatus: 'pending',
        errorMsg: null
      }
    });

    checkEmailAvailability(emailValue)
      .then(response => {
        if (response.available) {
          this.setState({
            email: {
              value: emailValue,
              validateStatus: 'success',
              errorMsg: null
            }
          });
        } else {
          this.setState({
            email: {
              value: emailValue,
              validateStatus: 'error',
              errorMsg: 'This Email is already registered'
            }
          });
        }
      }).catch(error => {
        // Marking validateStatus as success, Form will be recchecked at server
        this.setState({
          email: {
            value: emailValue,
            validateStatus: 'success',
            errorMsg: null
          }
        });
      });
  }

  validatePassword(password) {
    if (password.length < PASSWORD_MIN_LENGTH) {
      return {
        validateStatus: 'error',
        errorMsg: `Password is too short (Minimum ${PASSWORD_MIN_LENGTH} characters needed.)`
      }
    } else if (password.length > PASSWORD_MAX_LENGTH) {
      return {
        validateStatus: 'error',
        errorMsg: `Password is too long (Maximum ${PASSWORD_MAX_LENGTH} characters allowed.)`
      }
    } else {
      return {
        validateStatus: 'success',
        errorMsg: null,
      };
    }
  }
}

export default withSnackbar(withStyles(styles, { withTheme: true })(SignUp));