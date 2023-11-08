import { Routes, Route } from 'react-router-dom'

import { DefaultLayout } from './pages/DefaultLayout/DefaultLayout'

import Home from './pages/Home/Home'
import Account from "./pages/Account/Account.tsx";
import Bet from "./pages/Bet/Bet.tsx";
import Historic from "./pages/Historic/Historic.tsx";
import Result from "./pages/Result/Result.tsx";
import Support from "./pages/Support/Support.tsx";
import Games from "./pages/Games/Games.tsx";

export function Router() {
    return (
        <Routes>
            <Route path="/" element={<DefaultLayout />}>
                <Route path="/" element={<Home />} />
                <Route path="/perfil/:id" element={<Account />} />
                <Route path="/jogos" element={<Games />} />
                <Route path="/apostas/:id" element={<Bet />} />
                <Route path="/historicos/:id" element={<Historic />} />
                <Route path="/resultados" element={<Result />} />
                <Route path="/suporte" element={<Support />} />
            </Route>
        </Routes>
    )
}
