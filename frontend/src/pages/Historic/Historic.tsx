import { useParams } from "react-router-dom";
import {useEffect, useState} from "react";

function Historic() {
    let { id } = useParams();

    const [bets, setBets] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/apostas/apostadores/${id}`)
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                setBets(data.map((x, i) => (
                    <tr className="hover">
                        <th>{i + 1}</th>
                        <td>{x.jogo.id}</td>
                        <td>{x.data}</td>
                        <td>{x.valor}</td>
                        <td>{x.tipo}</td>
                        <td>{x.numeros.join(", ")}</td>
                    </tr>
                )));
            })
            .catch((err) => {
                setBets([]);
            });
    }, []);

    return (
        <div className="flex justify-center mt-[5%]">
            <div className="overflow-x-auto">
                <table className="table">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Jogo</th>
                        <th>Data</th>
                        <th>Valor</th>
                        <th>Tipo</th>
                        <th>Números</th>
                    </tr>
                    </thead>
                    <tbody>
                    {bets}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Historic;