
@echo off

SET USER=jetson
SET HOST=tarefas.3dzipado.com
SET PASSWORD=%1
SET REMOTE_PATH=/opt/app/app.jar
SET LOCAL_FILE=build\libs\app-0.0.1-SNAPSHOT.jar
SET REMOTE_COMMAND=/opt/app/execute.sh app.service

:: Faz o upload do arquivo usando scp
scp "%LOCAL_FILE%" %USER%@%HOST%:%REMOTE_PATH%  && %REMOTE_COMMAND%

:: Verifica se o upload foi bem-sucedido
IF 0 EQU 0 (
::IF %ERRORLEVEL% EQU 0 (
    echo Upload concluido com sucesso!
    :: Executa o comando remoto via ssh
    ssh %USER%@%HOST% %REMOTE_COMMAND%
    IF %ERRORLEVEL% EQU 0 (
        echo Comando remoto executado com sucesso!
    ) ELSE (
        echo Erro ao executar o comando remoto.
    )
) ELSE (
    echo Erro ao fazer upload do arquivo.
)