import { useEffect } from "react";
import { useParams } from "react-router-dom";
import { LinkCard } from "../Home/LinkCard.tsx";
import bets from "../../assets/bets.svg";
import { useNavigate } from "react-router-dom";
import { requestWithToken } from '../../components/api.tsx';

export default function Game() {
    let { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        fetch();
    }, [id]);

    const fetch = async () => {
        const data = await requestWithToken(`http://localhost:8080/jogos/${id}`, navigate);
        
        console.log(data);
    }

    return (
        <>
            <div className="flex self-center card bg-secondary text-white">
                <img src={bets} />
            </div>
            <div className="flex justify-center">
                <div className="flex justify-center">
                    <LinkCard
                        i={1}
                        title="Aposta Bicho"
                        subTitle="Aposte em até 5 animais!"
                        btn="Apostar"
                        href={`/nova-aposta/jogo/${id}?tipo=1`} />
                    <LinkCard
                        i={2}
                        title="Aposta Número"
                        subTitle="Aposte em dezenas, centenas ou tente acertar o número inteiro!"
                        btn="Apostar"
                        href={`/nova-aposta/jogo/${id}?tipo=2`} />
                </div>
            </div>
        </>
    );
}