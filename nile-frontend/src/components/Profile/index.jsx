import React from 'react';
import { getUserProfile } from '../../util/APIUtils';

class Profile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null
        }
    }

    loadUserProfile = username => {
        getUserProfile(username)
            .then(res => {
                this.setState({ user: res })
            }).catch(err => {
                
            })
    }

    componentDidMount() {
        const username = this.props.match.params.username;
        this.loadUserProfile(username);
    }

    componentDidUpdate(nextProps) {
        if(this.props.match.params.username !== nextProps.match.params.username) {
            this.loadUserProfile(nextProps.match.params.username);
        }        
    }

    render() {
        console.log(this.state.user);
        return (
            <></>
        )
    }
}

export default Profile;