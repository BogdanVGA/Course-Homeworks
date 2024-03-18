import React from "react";

const Bio = (props) => {
  
  const birthdate = props.birthdate;
  const description = props.description;
  const hobbies = props.hobbies;

  // check if programmer based on hobbies
  const isProgrammer = hobbies.includes("Java") || hobbies.includes("React");

  return (
    <div style={{ padding: "20px", border: "1px solid #ccc", borderRadius: "8px", marginTop: "20px" }}>
      <h3>Bio</h3>
      <p><strong>Birthdate:</strong> {birthdate}</p>
      <p><strong>Description:</strong> {description}</p>
      <p><strong>Hobbies:</strong></p>
      <ul>
        {hobbies.map((hobby, index) => (
          <li key={index}>{hobby}</li>
        ))}
      </ul>
      <p><strong>Programmer :</strong> {isProgrammer ? "TRUE" : "FALSE"}</p>
    </div>
  );
};

export default Bio;