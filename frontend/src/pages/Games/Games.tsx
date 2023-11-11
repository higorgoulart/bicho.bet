import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Games() {
    const [games, setGames] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:8080/jogos")
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                setGames(data.map((x, i) => (
                    <tr className="hover cursor-pointer" onClick={() => navigate(`/jogos/${x.id}`)}>
                        <th>{i + 1}</th>
                        <td>{x.loterica.nome}</td>
                        <td>{x.dataInicio}</td>
                        <td>{x.dataFim}</td>
                        <td>{x.valorAcumulado}</td>
                    </tr>
                )));
            })
            .catch((err) => {
                setGames([
                    (<tr className="hover cursor-pointer" onClick={() => navigate(`/jogos/${1}`)}>
                    <th>{1}</th>
                    <td>{"TESTE"}</td>
                    <td>{"01/01/2000"}</td>
                    <td>{"01/01/2020"}</td>
                    <td>{"1000"}</td>
                    </tr>)
                ]);
            });
    }, []);

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
                </table>
            </div>
        </div>
    );
}