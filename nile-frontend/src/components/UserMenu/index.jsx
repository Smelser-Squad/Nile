import { Button, ClickAwayListener, Grow, makeStyles, MenuItem, MenuList, Paper, Popper } from "@material-ui/core";
import React from 'react';
import { ACCESS_TOKEN } from "../../constants";
import { useStateValue } from "../../StateProvider";
import { useHistory } from 'react-router';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    button: {
        color: 'white',
        textTransform: 'none'
    }
}));

export default function UserMenu(props) {
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const anchorRef = React.useRef(null);
    const history = useHistory();
    const [{ isAuthenticated, currentUser }, dispatch] = useStateValue();
    const wrapper = React.createRef();

    const handleToggle = () => {
        setOpen((prevOpen) => !prevOpen);
    };

    const handleClose = (event, redirectTo) => {
        if (anchorRef.current && anchorRef.current.contains(event.target)) {
            return;
        }

        history.push(redirectTo);
        setOpen(false);
    };

    function handleListKeyDown(event) {
        if (event.key === 'Tab') {
            event.preventDefault();
            setOpen(false);
        }
    }

    // return focus to the button when we transitioned from !open -> open
    const prevOpen = React.useRef(open);
    React.useEffect(() => {
        if (prevOpen.current === true && open === false) {
            anchorRef.current.focus();
        }

        prevOpen.current = open;
    }, [open]);

    function handleLogout() {
        localStorage.removeItem(ACCESS_TOKEN);
        dispatch({
            type: 'USER_SIGN_OUT',
        });
        history.push('/');
    }

    return (
        <div ref={wrapper}>
            <Button
                className={classes.button}
                ref={anchorRef}
                aria-controls={open ? 'menu-list-grow' : undefined}
                aria-haspopup="true"
                onClick={handleToggle}
            >
                {isAuthenticated ? `Welcome back ${currentUser.username}` : 'Hello Guest'}
            </Button>
            <Popper open={open} anchorEl={anchorRef.current} role={undefined} transition disablePortal>
                {({ TransitionProps, placement }) => (
                    <Grow
                        {...TransitionProps}
                        style={{ transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom' }}
                    >
                        <Paper>
                            <ClickAwayListener onClickAway={handleClose}>
                                <MenuList autoFocusItem={open} id="menu-list-grow" onKeyDown={handleListKeyDown}>
                                    {isAuthenticated
                                        ?
                                        [
                                            <MenuItem onClick={handleClose} key='profile'>Profile</MenuItem>,
                                            <MenuItem onClick={handleClose} key='account'>My account</MenuItem>,
                                            <MenuItem onClick={handleLogout} key='logout'>Logout</MenuItem>
                                        ]
                                        :
                                        [
                                            <MenuItem onClick={e => handleClose(e, '/signin')} key='login'>Login</MenuItem>,
                                            <MenuItem onClick={e => handleClose(e, '/signup')} key='register'>Register</MenuItem>
                                        ]
                                    }
                                </MenuList>
                            </ClickAwayListener>
                        </Paper>
                    </Grow>
                )}
            </Popper>
        </div>
    )
}