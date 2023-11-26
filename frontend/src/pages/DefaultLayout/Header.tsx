/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from "react";
import secondaryLogo from '../../assets/monkey_solid_logo.svg'
import userIcon from '../../assets/user-icon.svg'
import { Link } from 'react-router-dom';
import Banana from '../../assets/vegan/banana.svg';

export function Header() {
    const [accountId, setAccountId] = useState(localStorage.getItem('idApostador'));

    interface Theme {
        tema: string;
        img: string;
        navbar: string;
        stroke: string;
      }

    const [theme, setTheme] = useState<Theme[]>([{
        tema: 'bg-primary',
        img: "logo.png",
        navbar: 'bg-primary',
        stroke: '#000'
    },
    ]);
    const [clickCount, setClickCount] = useState(0);

    useEffect(() => {
        const account = localStorage.getItem('idApostador');

        if (account !== accountId) {
            setAccountId(account);
        }
    }, [localStorage.getItem('idApostador')])

    const logout = () => {
        localStorage.clear();
        window.location.reload();
    }

    useEffect(() => {
        const handleClick = () => {
          setClickCount((prevCount) => prevCount + 1);
        };

        window.addEventListener('click', handleClick);

        return () => {
          window.removeEventListener('click', handleClick);
        };
    }, []);

    useEffect(() => {
        if (window.location.pathname !== '/institucional/veganos/') {
            setTheme({tema: 'bg-primary', img: secondaryLogo, stroke: 'currentColor'});
        } else if (window.location.pathname === '/institucional/veganos/') {
            setTheme({tema: 'bg-secondary', img: Banana, stroke: '#a0a0a0'});
        }
    }, [window.location.pathname, clickCount]);



    return (
        <div className={`navbar ${theme.tema}`}>
            <div className="navbar-start">
                <div className="dropdown">
                    <label tabIndex={0} className="btn btn-ghost btn-circle">
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke={theme.stroke}><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h7" /></svg>
                    </label>
                    <ul tabIndex={0} className="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-secondary rounded-box w-52">
                        <li><Link to="/jogos">Jogos</Link></li>
                        <li><Link to={`/historicos/${accountId}`}>Histórico</Link></li>
                        <li><Link to="/resultados">Resultados</Link></li>
                        <li><Link to="/suporte">Suporte</Link></li>
                    </ul>
                </div>
            </div>
            <div className="navbar-center">
                <Link to="/">
                    <img src={theme.img} className="w-16" />
                </Link>
            </div>
            <div className="navbar-end">
                <div className="dropdown dropdown-end">
                    <label tabIndex={0} className="btn btn-ghost btn-circle avatar">
                        <div className=" w-8 rounded-full">
                            <img src={userIcon} alt="" />
                        </div>
                    </label>
                    <ul tabIndex={0} className="mt-3 z-[1] p-2 shadow menu menu-sm dropdown-content bg-secondary rounded-box">
                        {accountId != null && accountId !== ""
                            ? (
                                <>
                                    <li><Link className="justify-between" to={`/perfil/${accountId}`}>Perfil</Link></li>
                                    <li><Link className="justify-between" to={`/perfil/deposito/${accountId}`}>Depositar</Link></li>
                                    <li><Link className="justify-between" to={`/perfil/emprestimo/${accountId}`}>Emprestimo</Link></li>
                                    <li><Link to="" onClick={logout}>Logout</Link></li>
                                </>
                            )
                            : (
                                <li><Link to="/login">Login</Link></li>
                            )}
                    </ul>
                </div>
            </div>
        </div>
    );
}
