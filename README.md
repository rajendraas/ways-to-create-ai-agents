# ways-to-create-ai-agents

## What is this ?

This is my learning repo.
This reposiroty contains some ways to implement AI Agents. There are many ways, I'm still learning how to implement it.

## Setup

I've setup `ollama` server with `llama3.2:1b` model in local to test the application.

1. Pull ollama docker image - 
```sh
docker pull ollama/ollama
```
2. Start `ollama` docker container -
```sh
docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
```
3. Run `llama3.2:1b` model -
```sh
docker exec -it ollama ollama run llama3.2:1b
```


### Test if model is working

We can test model with either direct chat promt in the terminal, or through API.
1. Terminal -
Run `docker exec -it ollama ollama run llama3.2:1b`, and then start chatting with the model
2. API -
Following the sample cURL for API test -
```sh
curl -X POST http://localhost:11434/api/generate -d '{
  "model": "llama3.2:1b",
  "prompt":"Here is a story about llamas eating grass"
 }'
```