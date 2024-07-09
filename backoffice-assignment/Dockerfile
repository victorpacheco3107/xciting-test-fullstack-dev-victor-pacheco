# Dockerfile for backoffice-assignment (Node.js)
FROM node:20

WORKDIR /usr/src/app

COPY package*.json ./
RUN npm install

COPY . .

# Compile TypeScript to JavaScript
RUN npm run build

# Copiar la carpeta views a la carpeta dist
RUN cp -r src/views dist/views

EXPOSE 3000

CMD ["npm", "start"]
