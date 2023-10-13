import Logo from "../assets/primary-logo.svg"
import Breadcrumb from "./Breadcrumb.tsx"

function Welcome() {
    return (
        <div className="flex justify-center">
            <div className="card w-[70%] bg-secondary mt-3 mb-4">
                <div className="flex">
                    <div className="card-body flex-4 pr-4">
                        <h2 className="card-title">Bem-vindo ao Bicho.bet!</h2>
                        <p>Registre-se agora e ganhe um bônus!</p>
                        <div className="card-actions">
                            <button className="btn btn-primary">Buy Now</button>
                            <button className="btn btn-primary">Google</button>
                            <button className="btn btn-primary">Xitter</button>
                        </div>
                        <Breadcrumb />
                    </div>
                    <div className="card-body">
                        <figure className="w-[85%]">
                            <img src={Logo} alt="logo" />
                        </figure>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Welcome;