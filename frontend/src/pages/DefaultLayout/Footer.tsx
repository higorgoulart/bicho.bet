import secondaryLogo from '../../assets/secondary-logo.svg'
import { Link } from 'react-router-dom'

export function Footer() {
    return (
        <footer className="footer p-10 bg-secondary text-base-content">
            <aside>
                <img src={secondaryLogo} />
                <p>
                    © 2023 Bicho.bet Todos os direitos reservados.
                    <br />
                    A Bicho.bet aceita apenas clientes com mais de 18 anos de idade.
                    <br />
                    Apostar pode ser viciante. Jogue responsavelmente.
                </p>
            </aside>
            <nav>
                <header className="footer-title">Links Úteis</header>
                <Link to="/institucional/bicho/" className="link link-hover">Apostas Bicho</Link>
                <Link to="/institucional/numero/" className="link link-hover">Apostas Número</Link>
                <Link to="/institucional/veganos/" className="link link-hover">Para Veganos</Link>
                <Link to="/institucional/duvidas/" className="link link-hover">Dúvidas Frequentes</Link>
            </nav>
            <nav>
                <header className="footer-title">Sobre Nós</header>
                <Link to="/institucional/servico/" className="link link-hover">Termos de Serviço</Link>
                <Link to="/institucional/politica/" className="link link-hover">Política de Privacidade</Link>
                <Link to="/institucional/dependencia/" className="link link-hover">Jogo Responsável</Link>
                <Link to="/institucional/carreira/" className="link link-hover">Carreira</Link>
            </nav>
        </footer>
    )
}
