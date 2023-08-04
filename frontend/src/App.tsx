import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
        <h1 className="text-pink-700">BICHO.BET</h1>
        <span className="loading loading-infinity loading-lg"></span>
    </>
  )
}

export default App
