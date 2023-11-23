import secondaryLogo from '../../assets/secondary-logo.svg'

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
                <a href="/institucional/bicho/" className="link link-hover">Apostas Bicho</a>
                <a href="/institucional/numero/" className="link link-hover">Apostas Número</a>
                <a href="/institucional/veganos/" className="link link-hover">Para Veganos</a>
                <a href="/institucional/duvidas/" className="link link-hover">Dúvidas Frequentes</a>
            </nav>
            <nav>
                <header className="footer-title">Sobre Nós</header>
                <a href="/institucional/servico/" className="link link-hover">Termos de Serviço</a>
                <a href="/institucional/politica/" className="link link-hover">Política de Privacidade</a>
                <a href="/institucional/dependencia/" className="link link-hover">Jogo Responsável</a>
                <a href="/institucional/carreira/" className="link link-hover">Carreira</a>
            </nav>
        </footer>
    )
}