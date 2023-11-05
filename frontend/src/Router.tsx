import { Routes, Route } from 'react-router-dom'

import { DefaultLayout } from './pages/DefaultLayout/DefaultLayout'

import Home from './pages/Home/Home'

export function Router() {
    return (
        <Routes>
            <Route path="/" element={<DefaultLayout />}>
                <Route path="/" element={<Home />} />
            </Route>
        </Routes>
    )
}
