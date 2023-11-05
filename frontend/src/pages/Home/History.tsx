import './Home.css'

export function History() {
    return (
        <div className="card w-[35%] bg-secondary text-white ml-16">
            <div className="card-body">
                <h2 className="card-title justify-end">Últimos Resultados</h2>
                <div className='history'>Verifique os últimos resultados!</div>
                <div className="card-actions justify-end mt-5">
                    <button className="btn btn-info glow-pink">Ir para Resultados</button>
                </div>
            </div>
        </div>
    )
}