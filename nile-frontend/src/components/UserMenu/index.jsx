import { Button, ClickAwayListener, Grow, makeStyles, MenuItem, MenuList, Paper, Popper } from "@material-ui/core";
import React from 'react';
import { ACCESS_TOKEN } from "../../constants";

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        flexDirection: 'column',
        marginLeft: '10px',
        marginRight: '10px',
        color: 'white'
    },
}));

export default function UserMenu(props) {
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const anchorRef = React.useRef(null);

    const handleToggle = () => {
        setOpen((prevOpen) => !prevOpen);
    };

    const handleClose = (event) => {
        if (anchorRef.current && anchorRef.current.contains(event.target)) {
            return;
        }

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
    }

    return (
        <>
            <Button
                ref={anchorRef}
                aria-controls={open ? 'menu-list-grow' : undefined}
                aria-haspopup="true"
                onClick={handleToggle}
            >
                {props.isAuthenticated ? props.currentUser : 'Guest'}
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
                                    {props.isAuthenticated
                                        ?
                                        [
                                            <MenuItem onClick={handleClose} key='profile'>Profile</MenuItem>,
                                            <MenuItem onClick={handleClose} key='account'>My account</MenuItem>,
                                            <MenuItem onClick={handleClose} key='logout'>Logout</MenuItem>
                                        ]
                                        :
                                        [
                                            <MenuItem onClick={handleClose} key='login'>Login</MenuItem>,
                                            <MenuItem onClick={handleClose} key='register'>Register</MenuItem>
                                        ]
                                    }
                                </MenuList>
                            </ClickAwayListener>
                        </Paper>
                    </Grow>
                )}
            </Popper>
        </>
    )
}