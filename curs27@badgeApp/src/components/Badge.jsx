import React from "react";

class Badge extends React.Component {

  render() {
    
    return (
      <div
        style={{
          border: "1px solid white",
          width: "320px",
          borderRadius: "8px",
          padding: "20px",
          display: "flex",
          height: "80px",
        }}
      >
        <div style={{ width: "75%" }}>
          <p>{this.props.firstName}</p>
          <p>{this.props.lastName}</p>
        </div>
        <img src={this.props.avatarImage} height="90px" alt="Avatar" />
      </div>
    );
  }
}

export default Badge;