export default function Animal({ title, numbers, src, onClick, color }) {
    return (
        <div className="flex flex-col items-center m-5 w-[8.5rem] cursor-pointer" onClick={() => onClick(src)}>
            <img className="object-cover" src={src} />
            <h1 className={`card-title text-${color == undefined ? "white" : color} self-center mt-2`}>{title}</h1>
            <h2 className={`text-${color} self-center`}>{numbers}</h2>
        </div>
    );
}
