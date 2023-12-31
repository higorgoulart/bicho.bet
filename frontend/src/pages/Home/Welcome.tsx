import { Link } from 'react-router-dom'
import { Breadcrumb } from './Breadcrumb'

import './Home.css'
import { useEffect, useState } from 'react';

export function Welcome({href}) {
    const [accountId, setAccountId] = useState(localStorage.getItem('idApostador'));
    
    useEffect(() => {
        const account = localStorage.getItem('idApostador');

        if (account !== accountId) {
            setAccountId(account);
        }
    }, [localStorage.getItem('idApostador')])

    return (
        <div className="flex justify-center mt-[5%]">
            <div className="card w-[80%] bg-secondary mt-3 mb-4 p-4 over overflow-clip">
                <div className="flex">
                    <div className="card-body flex-4 pr-4">
                        <h1 className="card-title text-white">Bem-vindo ao Bicho.bet!</h1>
                        <div className='mt-4 mb-4 text-white'>Registre-se agora e ganhe um bônus!</div>
                        <div className="card-actions">
                            {accountId != null && accountId !== "" 
                                ? (
                                    <>
                                        <Link className='btn btn-info glow-pink text-white' to={`/perfil/deposito/${accountId}`}>Depositar</Link>
                                    </>
                                )
                                : (
                                    <Link className="btn btn-accent glow-blue text-white" to={href}>Cadastre-se</Link>
                                )}
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