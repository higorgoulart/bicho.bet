import { Link } from "react-router-dom";

export function LinkCard({ i, title, subTitle, btn, href }) {
    return (
        <div className={"card w-[35%] bg-secondary text-white " + (i % 2 == 0 ? "ml-16" : "mr-16")}>
            <div className="card-body">
                <h2 className={"card-title " + (i % 2 == 0 ? "justify-end" : "")}>{title}</h2>
                <div className={(i % 2 == 0 ? "flex justify-end" : "")}>{subTitle}</div>
                <div className={"card-actions mt-5 " + (i % 2 == 0 ? "justify-end" : "")}>
                    <Link className="btn btn-info glow-pink" to={href}>{btn}</Link>
                </div>
            </div>
        </div>
    )
}
