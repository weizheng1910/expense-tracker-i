import React from 'react';
import axios from 'axios';

export default class Register extends React.Component {
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

  validate(){
    if(this.state.user.length <= 3){
      alert('Username must be more than 3 characters long.');
      return false;
    } else if(this.state.password.length <= 3){
      alert('Password must be more than 3 characters long.');
      return false;
    } else {
      return true;
    }
  }

  handleSubmit() {
    const data = {
      username: this.state.user,
      password: this.state.password
    }

    if(this.validate()){
      axios.post(`/api/newAccount`, data).then((response) => {
        alert('Account created successfully! Please log in with your new account.')
        window.location.href = "/";
      });
    }
    
  };

  render() {
    // intermediate results 

    /*
    let searchResults = this.state.results.map(result =>{
      return <div className='d-flex'> 
        <div className='w-75'>
          {result.name} 
        </div>
        <div className='w-25'>
          <button onClick={(evt)=>{this.choosePerson(result.url)}}>Show</button>
        </div>
      </div>
    })

    let searchResultsWithHeader = (this.state.results.length != 0 
      ? <div>
          <h5>Search Results:</h5>
          {searchResults}
        </div> 
      : <div></div>)

      */

      return (
        <div class="input-group input-group-lg">
          <input type="text" placeholder="Username…" onChange={(evt)=>{this.updateUser(evt.target.value)}} value={this.state.user}/>
          <input type="password" placeholder="Password…" onChange={(evt)=>{this.updatePassword(evt.target.value)}} value={this.state.password}/>
          <div class="input-group-append">
            <button onClick={(evt) => {this.handleSubmit()}} class="btn">Register</button>
          </div>
        </div>
        );
    }
  }

