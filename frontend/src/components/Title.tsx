export function Title({ children, subTitle }) {
    return (
        <div className="flex flex-col self-center">
            {children}
            <h1 className="card-title text-white self-center mt-5">{subTitle}</h1>
        </div>
    )
}