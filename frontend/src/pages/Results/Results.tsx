import {Title} from "../../components/Title.tsx";
import {useEffect, useState} from "react";
import recentResults from "../../assets/recent-results.svg";

export default function Results() {
    const [results, setResults] = useState([]);
    const [game, setGame] = useState("");

    useEffect(() => {
        const filter = game !== "" ? "?filter=jogo.id+equal+" + game : ""

        fetch(`http://localhost:8080/resultados${filter}`)
            .then((res) => res.json())
            .then((data) => {
                console.log(data);

                // TODO: tirar quando tiver trazendo dado a request
                if (data.length == 0) {
                    data = [
                        {
                            data: new Date(2023, 9, 24),
                            jogo: 1,
                            numeros: [
                                4029,
                                1697,
                                7893,
                                8656,
                                6753
                            ]
                        }
                    ];
                }
                setResults(data.map((x, i) => (
                    <tr>
                        <th>{i + 1}</th>
                        <td>{x.data.toLocaleDateString()}</td>
                        <td>{x.jogo.loterica.nome}</td>
                        <td>{x.numeros[0]}</td>
                        <td>{x.numeros[1]}</td>
                        <td>{x.numeros[2]}</td>
                        <td>{x.numeros[3]}</td>
                        <td>{x.numeros[4]}</td>
                    </tr>
                )));
            })
            .catch((err) => {
                setResults([]);
            });
    }, [game]);

    return (
        <>
            <Title subTitle="">
                <img src={recentResults} />
            </Title>
            <div className="flex flex-col items-center">
                <div className="flex-row items-center justify-evenly mb-6">
                    <div className="flex-col">
                        <label className="label">
                            <span className="label-text">Jogo</span>
                        </label>
                        <input type="text" placeholder="Digite aqui" className="input input-bordered w-full max-w-xs" onChange={(e) => setGame(e.target.value)} />
                    </div>
                </div>
                <div className="overflow-x-auto">
                    <table className="table">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Data</th>
                                <th>Jogo</th>
                                <th>1º</th>
                                <th>2º</th>
                                <th>3º</th>
                                <th>4º</th>
                                <th>5º</th>
                            </tr>
                        </thead>
                        <tbody>{results}</tbody>
                    </table>
                </div>
            </div>
        </>

    );
}