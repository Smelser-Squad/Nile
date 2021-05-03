import { Component } from "react";
import { withStyles, Menu, MenuItem } from "@material-ui/core";
import { ACCESS_TOKEN } from "../../constants";

const styles = (theme) => ({
    root: {
      flexGrow: 1,
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    title: {
      flexGrow: 1,
    },
  });

class UserMenu extends Component {

    handleLogout() {
        localStorage.removeItem(ACCESS_TOKEN);
    }

    render() {
        return (
            <Menu>
                {this.props.isAuthenticated 
                    ? <div>
                        <MenuItem>Profile</MenuItem>
                        <MenuItem onClick={this.handleLogout}>Logout</MenuItem>
                      </div>
                    : <MenuItem>Login</MenuItem>
                }
            </Menu>
        )
    }
}

export default withStyles(styles, { withTheme: true })(UserMenu);