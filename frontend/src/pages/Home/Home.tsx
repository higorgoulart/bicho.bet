import { Welcome } from './Welcome';
import { Results } from './Results';
import { History } from './History';
import { Payment } from './Payment';

import './Home.css'



function Home() {
    return (
        <>
            <Welcome />
            <div className="flex justify-center my-[5%]">
                <Results />
                <History />
            </div>
            <div className="flex justify-center mt-[3%]">
                <div className='text-white text-xl'>Métodos de Pagamento</div>
            </div>
            <div className="flex justify-center mb-[5%]">
                <Payment />
            </div>
        </>
    )
}

export default Home;