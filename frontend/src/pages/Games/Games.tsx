import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { requestWithToken } from '../../components/api.tsx';

export default function Games() {
    const [games, setGames] = useState([]);
    const navigate = useNavigate();
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);  

    const fetch = async () => {
        const data = await requestWithToken(`http://localhost:8080/jogos?p=${page}`, navigate);
        setTotalPages(data.totalPages);
        setGames(data.content.map((x, i) => (
            <tr className="hover cursor-pointer" onClick={() => navigate(`/jogos/${x.id}`)}>
                <th>{i + 1 + page}</th>
                <td>{x.loterica.nome}</td>
                <td>{x.dataInicio}</td>
                <td>{x.dataFim}</td>
                <td>{x.valorAcumulado}</td>
            </tr>
        )));
        
    }
    
    useEffect(() => {
        fetch();
    }, [page]);

    return (
        <div className="flex justify-center">
            <div className="overflow-x-auto">
                <table className="table">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Lotérica</th>
                        <th>Início</th>
                        <th>Fim</th>
                        <th>Valor acumulado</th>
                    </tr>
                    </thead>
                    <tbody>
                    {games}
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
    );
}