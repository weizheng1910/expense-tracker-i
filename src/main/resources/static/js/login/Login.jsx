import React from 'react';

export default class Login extends React.Component {
  constructor(){
    super()
    this.state = {
      user: '',
      password: '',
    }
  }

  updatePassword(pw){
    this.setState({password: pw})
  }

  updateUser(user){
    this.setState({user: user})
  }

  handleSubmit() {
    
    const data = new FormData();
    data.append("username", this.state.user);
    data.append("password", this.state.password);

    // fetch API 
    fetch("perform_login", {
      method: "POST",
      body: new URLSearchParams(data),
    })
    .then((v) => {
      if (v.redirected) window.location = v.url;
    })
    .catch((e) => console.warn(e));

  };

  render() {
      return (
        <div class="input-group input-group-lg">
          <input type="text" placeholder="Username…" onChange={(evt)=>{this.updateUser(evt.target.value)}} value={this.state.user}/>
          <input type="password"  placeholder="Password…" onChange={(evt)=>{this.updatePassword(evt.target.value)}} value={this.state.password}/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" onClick={(evt) => {this.handleSubmit()}} class="btn">Sign in</button>
        </div>
        </div>
        );
    }
  }

