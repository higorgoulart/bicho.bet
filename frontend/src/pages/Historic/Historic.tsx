import { useParams } from "react-router-dom";
import {useEffect, useState} from "react";
import {Title} from "../../components/Title.tsx";
import historic from "../../assets/historic.svg";
import { useNavigate } from "react-router-dom";
import { requestWithToken } from '../../components/api.tsx';

export default function Historic() {
    let { id } = useParams();
    const navigate = useNavigate();
    const [bets, setBets] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        fetch();
    }, [id, page]);

    const fetch = async () => {
        const data = await requestWithToken(`http://localhost:8080/apostadores/${id}/historicos`, navigate);
        console.log(data);
        setTotalPages(data.totalPages);
        setBets(data.content.map((x, i) => (
            <tr className="hover">
                <th>{i + 1 + page}</th>
                <td>{x.data}</td>
                <td>{x.loterica}</td>
                <td>{x.tipoAposta}</td>
                <td>{x.numerosAposta.join(", ")}</td>
                <td>{x.numerosResultado.join(", ")}</td>
                <td>R$ {x.aposta}</td>
                <td>R$ {x.premio}</td>
            </tr>
        )));
    }

    return (
        <>
            <Title subTitle="Seu histórico de apostas">
                <img src={historic} />
            </Title>
            <div className="flex justify-center">
                <div className="overflow-x-auto">
                    <table className="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Data</th>
                            <th>Lotérica</th>
                            <th>Tipo de Aposta</th>
                            <th>Aposta</th>
                            <th>Resultado</th>
                            <th>Valor</th>
                            <th>Prêmio</th>
                        </tr>
                        </thead>
                        <tbody>
                        {bets}
                        </tbody>
                        <tfoot>
                        <div className="join">
                            <button onClick={() => page > 0 ? setPage(page - 1) : null} className="join-item btn">«</button>
                            <div className="join-item btn">{page + 1}</div>
                            <button onClick={() => page < totalPages - 1 ? setPage(page + 1) : null} className="join-item btn">»</button>
                        </div>
                        </tfoot>
                    </table>
                </div>
            </div>
        </>
    );
}