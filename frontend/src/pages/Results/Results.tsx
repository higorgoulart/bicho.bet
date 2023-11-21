import {Title} from "../../components/Title.tsx";
import {useEffect, useState} from "react";
import recentResults from "../../assets/recent-results.svg";

export default function Results() {
    const [results, setResults] = useState([]);
    const [date, setDate] = useState("");
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        const filter = date !== "" ? "&filter=data\+equal\+" + date + "T00:00:00" : ""

        fetch(`http://localhost:8080/resultados?p=${page}${filter}`)
            .then((res) => res.json())
            .then((data) => {
                setTotalPages(data.totalPages);

                setResults(data.content.map((x, i) => (
                    <tr>
                        <th>{i + 1}</th>
                        <td>{new Date(x.data).toLocaleDateString()}</td>
                        <td>{x.jogo}</td>
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
    }, [date, page]);

    return (
        <>
            <Title subTitle="">
                <img src={recentResults} />
            </Title>
            <div className="flex flex-col items-center">
                <div className="flex-row items-center justify-evenly mb-6">
                    <div className="flex-col">
                        <label className="label">
                            <span className="label-text">Data</span>
                        </label>
                        <input type="date" placeholder="Digite aqui" className="input input-bordered w-full max-w-xs" onChange={(e) => setDate(e.target.value)} />
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