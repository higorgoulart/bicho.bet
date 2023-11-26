import { Outlet } from 'react-router-dom'

import { Header } from './Header'
import { Footer } from './Footer'
import { ThemeProvider } from '../../components/ThemeProvider'

export function DefaultLayout() {
    return (
        <ThemeProvider>
            <div className='flex flex-col h-screen justify-between'>
                <Header />
                <Outlet />
                <Footer />
            </div>
        </ThemeProvider>
    )
}
