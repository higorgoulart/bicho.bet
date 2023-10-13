import Logo from "../assets/secondary-logo.svg"


function Header() {
    return (
        <div className="navbar fixed top-0 w-full bg-primary p-4">
            <div>
                <img src={Logo} alt="Logo" className="h-10" />
            </div>
            <div className="relative left-[2%]">
                <ul className="menu menu-horizontal px-1 text-base">
                    <li><a className="text-[#EA0FFD]">Apostas</a></li>
                    <li><a className="text-[#00B2FF]">Histórico</a></li>
                    <li><a className="text-[#EA0FFD] ml-2">Resultados</a></li>
                    <li><a className="text-[#00B2FF] ml-2">Suporte</a></li>
                </ul>
            </div>
            <div className="relative left-[30%]">
                <div>
                    <button className="bg-white text-[#00B2FF] border-[#00B2FF] align-middle w-32 h-10 pt-2">Login</button>
                </div>
                <div className="ml-3">
                    <button className="bg-[#EA0FFD] text-white border-[#EA0FFD] align-middle w-32 h-10 p-0">Registrar-se</button>
                </div>
            </div>
        </div>
    )
}

export default Header;