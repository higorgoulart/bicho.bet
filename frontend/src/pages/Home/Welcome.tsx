import { Breadcrumb } from './Breadcrumb'

import './Home.css'

export function Welcome() {
    return (
        <div className="flex justify-center mt-[5%]">
            <div className="card w-[80%] bg-secondary mt-3 mb-4 p-4 over overflow-clip">
                <div className="flex">
                    <div className="card-body flex-4 pr-4">
                        <h1 className="card-title text-white">Bem-vindo ao Bicho.bet!</h1>
                        <div className='mt-4 mb-4 text-white'>Registre-se agora e ganhe um bônus!</div>
                        <div className="card-actions">
                            <button className="btn btn-accent text-white glow-blue">Registrar-se</button>
                            <button className="btn btn-secondary border-[#5A5A5A]"><img src="src/assets/google-logo.png" className="w-5"/></button>
                            <button className="btn btn-secondary border-[#5A5A5A]"><img src="src/assets/facebook-logo.png" className="w-6"/></button>
                        </div>
                        <Breadcrumb />
                    </div>
                    <div className="card-body">
                        <figure className="w-[85%]">
                            <img src="src/assets/primary-logo.svg" alt="logo" />
                        </figure>
                    </div>
                </div>
            </div>
        </div>
    )
}