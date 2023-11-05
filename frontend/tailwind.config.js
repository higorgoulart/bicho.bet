/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {},
  },
  daisyui: {
    themes: [
      {
        mytheme: {
          primary: "#252526",
          secondary: "#3E3E42",
          accent: "#00B2FF",
          info: "#EA0FFD"
        },
      },
    ],
  },
  plugins: [require("daisyui")],
}

