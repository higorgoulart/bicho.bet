/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {},
    screens: {
      xs: "375px",
      xss: "410px",
      ssm: "530px",
      sm: "640px",
      md: "768px",
      smd: "890px",
      lmd: "990px",
      lg: "1024px",
      xl: "1150px",
      "2xl": "1536px",
      "3xl": "1890px",
    },
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

