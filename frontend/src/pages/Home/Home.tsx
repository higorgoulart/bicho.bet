import { Welcome } from './Welcome';
import { Payment } from './Payment';
import { LinkCard } from "./LinkCard";

import './Home.css'

export default function Home() {
    const accountId = 1;

    return (
        <>
            <Welcome />
            <div className="flex justify-center my-[5%]">
                <LinkCard
                    i={1}
                    title="Histórico"
                    subTitle="Verifique as suas últimas apostas!"
                    btn="Ir para Histórico"
                    href={`/historicos/${accountId}`} />
                <LinkCard
                    i={2}
                    title="Últimos Resultados"
                    subTitle="Verifique os últimos resultados!"
                    btn="Ir para Resultados"
                    href="/resultados" />
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