import React from 'react'
import Badge from './components/Badge'
import Bio from './components/Bio';
import './App.css'

const badges = [
  {
    firstName: "Johhny",
    lastName: "Depp",
    img: "https://variety.com/wp-content/uploads/2023/09/GettyImages-1490945319.jpg?w=1024",
    birthdate: "June 9, 1963",
    description: "An American actor known for his versatility.",
    hobbies: ["Acting", "Music", "Painting"]
  },
  {
    firstName: "Tommy",
    lastName: "Williams",
    img: "https://avatars.githubusercontent.com/u/1378042?v=4",
    birthdate: "March 21, 1978",
    description: "Software developer with a passion for open source.",
    hobbies: ["Coding", "Hiking", "React"]
  },
  {
    firstName: "Clark",
    lastName: "Kent",
    img: "https://t0.gstatic.com/licensed-image?q=tbn:ANd9GcT2xYTv3ig7zGLvs0ABliV1ZMWG-0waOX_P6nd03SJnDLVoTiSnvuCMJ-dNpQhhYXTC",
    birthdate: "February 29, 1938",
    description: "Reporter at Daily Planet with a strong sense of justice.",
    hobbies: ["Writing", "Flying", "Java"]
  }
];

class App extends React.Component {

  render() {

    return (
      <div id='top'>
        <h2>Curs 27 - Badge App Exercise</h2>
        <div id='container'>
          {badges.map((crtBadge, index) => (
            <div key={index}>
              <Badge id='badge'
                firstName={crtBadge.firstName}
                lastName={crtBadge.lastName}
                avatarImage={crtBadge.img}
              />
              <Bio id='bio'
                birthdate={crtBadge.birthdate}
                description={crtBadge.description}
                hobbies={crtBadge.hobbies}
              />
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default App;