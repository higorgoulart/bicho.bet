import gorila from './assets/gorila.png'
import './App.css'

function App() {
  return (
    <>
        <div className="alert alert-success fixed flex top-0 left-0 items-center justify-center border-none">
            <span>Cadastre-se e receba um bônus de até R$ 200,00! 🔥</span>
            <a href="" className="btn btn-sm btn-primary">Receber</a>
        </div>
        <img src={gorila} alt="Gorila" />
        <h1 className="text-primary">BICHO.BET</h1>
        <span className="loading loading-infinity loading-lg"></span>
    </>
  )
}

export default App
