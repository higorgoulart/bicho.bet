import { Outlet } from 'react-router-dom'

import { Header } from './Header'
import { Footer } from './Footer'

export function DefaultLayout() {
    return (
        <div className='flex flex-col h-screen justify-between'>
            <Header />
            <Outlet />
            <Footer />
        </div>
    )
}