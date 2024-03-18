import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'

export const App = () => {

  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <a href="https://www.devmind.ro/" target="_blank">
          <img
            src={'https://www.devmind.ro/static/imgs/big-f65889fd23d95255a2cf6088b3f3eb53.png'}
            className="logo"
            alt="Devmind logo"
          />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Devmind + React</h1>
      <div className='card'>
        <ul className='button-list'>
          <li className='list-item'>
            <button onClick={() => setCount((count) => count + 1)}>
              Click to raise count. Count is: {count}
            </button>
          </li>
          <li className='list-item'>
            <button onClick={() => setCount(0)}>
              Click to reset count
            </button>
          </li>
        </ul>
        <p>
          Welcome! This is my first React app!
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Devmind or React logo to learn more
      </p>
    </>
  );
}
