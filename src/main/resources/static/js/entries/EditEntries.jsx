import React from "react";
import axios from "axios";

export default class EditEntries extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: props.id,
      date: props.date,
      activity: props.activity,
      cost: props.cost
    };

    this.updateEntry = this.updateEntry.bind(this);
    this.deleteEntry = this.deleteEntry.bind(this);
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

  updateEntry(){
    const data = {
      id: this.state.id,
      activity: this.state.activity,
      cost: this.state.cost,
      date: this.state.date
    };

    if(this.validate()){
      axios
        .post(`/api/editEntries`, data)
        .then((res) => {
          this.props.repopulateList();
          alert('Entry Saved!');
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }

  validate(){
    if(isNaN(this.state.cost) || this.state.cost < 0){
      alert('Please input a positive integer for cost');
      return false;
    } else if(this.state.activity.length == 0 || !isNaN(this.state.activity)){
      alert('Activity cannot be empty and cannot be a number');
      return false;
    } else {
      return true;
    }
  }

  deleteEntry(){

    var id = this.state.id;

    axios
      .delete(`/api/deleteEntries/${id}`)
      .then((response) => {
        this.props.repopulateList();
      });
  }

  render(){
    return (
      <div class="input-group input-group-lg mx-3">
        <input type="date" value={this.state.date} onChange={(evt)=>{this.updateDate(evt.target.value)}}/>
        <input type="text" placeholder="Activity" onChange={(evt)=>{this.updateActivity(evt.target.value)}} value={this.state.activity}/>
        <input type="text" placeholder="Cost" onChange={(evt)=>{this.updateCost(evt.target.value)}} value={this.state.cost}/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" onClick={(evt) => {this.updateEntry()}}>Save</button>
          <button class="btn btn-outline-secondary" onClick={(evt) => {this.deleteEntry()}} >Delete</button>
        </div>
      </div>
  )}
}