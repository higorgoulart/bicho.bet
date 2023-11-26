import { Title } from "../../components/Title.tsx";
import { useEffect, useState } from "react";
import recentResults from "../../assets/recent-results.svg";
import { useNavigate } from "react-router-dom";
import { requestWithToken } from '../../components/api.tsx'; // Adjust the path accordingly

export default function Results() {
  const navigate = useNavigate();
  const [results, setResults] = useState([]);
  const [date, setDate] = useState("");
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const filter = date !== "" ? `&filter=data\+equal\+${date}T00:00:00` : "";

        const data = await requestWithToken(`http://localhost:8080/resultados?p=${page}${filter}`, navigate);
        setTotalPages(data.totalPages);

        setResults(data.content.map((x, i) => (
          <tr key={i}>
            <th>{i + 1 + page}</th>
            <td>{new Date(x.data).toLocaleDateString()}</td>
            <td>{x.jogo}</td>
            <td>{x.numeros[0]}</td>
            <td>{x.numeros[1]}</td>
            <td>{x.numeros[2]}</td>
            <td>{x.numeros[3]}</td>
            <td>{x.numeros[4]}</td>
          </tr>
        )));
      } catch (error) {
        setResults([]);
        // Handle errors, e.g., set an error state or display an error message
        console.error(error);
      }
    };

    fetchData();
  }, [date, page]);

  return (
    <>
      <Title subTitle="">
        <img src={recentResults} alt="Recent Results" />
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
