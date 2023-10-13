/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  daisyui: {
    themes: [
      {
        mytheme: {
          primary: "#252526",
          secondary: "#3E3E42",
        },
      },
    ],
  },
  plugins: [require("daisyui")],
}

