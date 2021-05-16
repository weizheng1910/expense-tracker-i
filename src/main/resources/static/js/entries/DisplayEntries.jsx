import React from "react";
import Entries from "./Entries.jsx";
import EditEntries from "./EditEntries.jsx";
import axios from "axios";

export default class DisplayEntries extends React.Component {
   constructor(props) {
    super(props);
      this.state = {
        entries: []
      };
      this.showAll = this.showAll.bind(this);
    }

  showAll () {
    axios
      .get(`/api/getEntries`)
      .then((res) => {
        this.setState({ entries: res.data });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  componentDidMount(){
    this.showAll();
  }

  render(){

    let searchResults = this.state.entries.map(entry =>{
      return <div class="row"> 
        <EditEntries 
          activity={entry.activity}
          id={entry.id}
          cost={entry.cost}
          date={entry.date}
          repopulateList={this.showAll}
        />
      </div>
    })



    return (
      <div>
        <Entries repopulateList={this.showAll}/>
        <br></br>
        <br></br>
        <br></br>
        <div>{searchResults}</div>
       
      </div>
  )}
}
