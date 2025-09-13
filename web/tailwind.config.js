/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        primary: '#d50000',
        secondary: '#b71c1c',
        accent: '#ff5252',
      }
    },
  },
  plugins: [],
}