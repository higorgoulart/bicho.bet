import { Routes, Route, Navigate } from 'react-router-dom'

import { DefaultLayout } from './pages/DefaultLayout/DefaultLayout'

import Home from './pages/Home/Home'
import AdminRedirect from './pages/Admin/AdminRedirect.tsx';
import Account from "./pages/Account/Account.tsx";
import Bet from "./pages/Bet/Bet.tsx";
import NewBet from "./pages/NewBet/NewBet.tsx";
import Historic from "./pages/Historic/Historic.tsx";
import Results from "./pages/Results/Results.tsx";
import Support from "./pages/Support/Support.tsx";
import Games from "./pages/Games/Games.tsx";
import Game from "./pages/Game/Game.tsx";
import * as institucional from './pages/Institucional/Institucional.tsx';


export function Router() {
    return (
        <Routes>
            <Route path="/" element={<DefaultLayout />}>
                <Route path="/" element={<Home />} />
                <Route path="/perfil/:id" element={<Account />} />
                <Route path="/jogos" element={<Games />} />
                <Route path="/jogos/:id" element={<Game />} />
                <Route path="/nova-aposta/jogo/:id" element={<NewBet />} />
                <Route path="/apostas/:id" element={<Bet />} />
                <Route path="/historicos/:id" element={<Historic />} />
                <Route path="/resultados" element={<Results />} />
                <Route path="/suporte" element={<Support />} />
                <Route path="/institucional/bicho/" element={<institucional.Bicho />} />
                <Route path="/institucional/numero/" element={<institucional.Numero />} />
                <Route path="/institucional/veganos/" element={<institucional.Veganos />} />
                <Route path="/institucional/duvidas/" element={<institucional.Duvidas />} />
                <Route path="/institucional/servico/" element={<institucional.Servico />} />
                <Route path="/institucional/politica/" element={<institucional.Politica />} />
                <Route path="/institucional/dependencia/" element={<institucional.Dependencia />} />
                <Route path="/institucional/carreira/" element={<institucional.Carreira />} />
                <Route path="/admin" element={<AdminRedirect/>}/>
            </Route>
        </Routes>
    )
}
