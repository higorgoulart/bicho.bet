import LastResults from "./LastResults";
import Welcome from "./Welcome";
import History from "./History";

function Home() {
    return (
        <div className="bg-primary w-full h-full absolute top-[13.8%]">
            <Welcome />
            <div>
                <LastResults />
                <History />
            </div>
        </div>
    )
}

export default Home;