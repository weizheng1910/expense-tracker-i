import React from "react";
import axios from "axios";

export default class Entries extends React.Component {
  constructor(props){
    super(props)
    this.state = {
      date: '',
      activity: '',
      cost: '',
    }


  }

  updateDate(d){
    this.setState({date: d})
  }

  updateCost(c){
    this.setState({cost: c})
  }

  updateActivity(a){
    this.setState({activity: a})
  }

  clearSlate(){
    this.setState({
      activity: '',
      cost: '',
      date: ''
    })
  }

  validate(){
    if(isNaN(this.state.cost) || this.state.cost < 0){
      alert('Please input a positive integer for cost');
      return false;
    } else if(this.state.activity.length == 0|| !isNaN(this.state.activity)){
      alert('Activity cannot be empty and cannot be a number');
      return false;
    } else if(this.state.date.length == 0){ 
      alert('Date cannot be empty');
      return false;
    } else {
      return true;
    }
  }

  handleSubmit() {
    if(this.validate()){
      axios
        .post(`/api/entries`, this.state)
        .then((res) => {
          this.props.repopulateList();
          this.clearSlate();
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };
    

  render() {
      return (
          <div class="input-group input-group-lg">
              <input class="form-control" type="date" class="span3" placeholder="Date" onChange={(evt)=>{this.updateDate(evt.target.value)}} value={this.state.date}/>
              <input class="form-control" type="text" class="span3" placeholder="Activity" onChange={(evt)=>{this.updateActivity(evt.target.value)}} value={this.state.activity}/>
              <input class="form-control" type="text" class="span3" placeholder="Cost" onChange={(evt)=>{this.updateCost(evt.target.value)}} value={this.state.cost}/>
            <div class="input-group-append">
              <button class="btn btn-outline-secondary" onClick={(evt) => {this.handleSubmit()}} class="btn">Create Entry</button>
            </div>
          </div>
        );
    }
  }

