import secondaryLogo from '../../assets/monkey_solid_logo.svg'
import userIcon from '../../assets/user-icon.svg'


export function Header() {
    const accountId = 1;

    return (
        <div className="navbar bg-primary  ">
            <div className="navbar-start">
                <div className="dropdown">
                    <label tabIndex={0} className="btn btn-ghost btn-circle">
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h7" /></svg>
                    </label>
                    <ul tabIndex={0} className="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-secondary rounded-box w-52">
                        <li><a href="/jogos">Jogos</a></li>
                        <li><a href={`/historicos/${accountId}`}>Histórico</a></li>
                        <li><a href="/resultados">Resultados</a></li>
                        <li><a href="/suporte">Suporte</a></li>
                    </ul>
                </div>
            </div>
            <div className="navbar-center">
                <a href="/">
                    <img src={secondaryLogo} className="w-16" />
                </a>
            </div>
            <div className="navbar-end">
                <div className="dropdown dropdown-end">
                    <label tabIndex={0} className="btn btn-ghost btn-circle avatar">
                        <div className=" w-8 rounded-full">
                            <img src={userIcon} />
                        </div>
                    </label>
                    <ul tabIndex={0} className="mt-3 z-[1] p-2 shadow menu menu-sm dropdown-content bg-secondary rounded-box">
                        <li><a className="justify-between" href={`/perfil/${accountId}`}>Perfil</a></li>
                        <li><a>Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    );
}