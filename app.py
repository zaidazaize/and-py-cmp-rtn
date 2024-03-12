from fastapi import FastAPI , UploadFile
import shutil
import os
import uuid
import subprocess

app = FastAPI()


@app.get("/")
def read_root():
    return {"Hello": "World"}

@app.post("/uploadfile/")
def handle_file_upload(file: list[UploadFile]):
    if(not os.path.exists("upload")):
        os.mkdir("upload")
    result ={}
    for upload_file in file:

        uniqueid = str(uuid.uuid4())
        os.mkdir(f"upload/{uniqueid}")
        if(not os.path.exists(f"upload/{uniqueid}/build")):
            os.mkdir(f"upload/{uniqueid}/build")
        
        with open(f"upload/{uniqueid}/" + upload_file.filename, "wb") as buffer:
            shutil.copyfileobj(upload_file.file, buffer)

        command = f"javac -d upload/{uniqueid}/build  upload/{uniqueid}/{upload_file.filename}"
        process = subprocess.Popen(command, shell=True)
        output , error = process.communicate()
        javafile = upload_file.filename.split(".")[0]

        # Run the compiled file
        command = f"java -cp upload/{uniqueid}/build {javafile}"
        process = subprocess.Popen(command,stdout=subprocess.PIPE, shell=True)
        output , error = process.communicate()

        if error:
            result[uniqueid] = {"filename": upload_file.filename, "output": error.decode().strip()}
        else:
            result[uniqueid] = {"filename": upload_file.filename, "output": output.decode().strip()}


    return result



import uvicorn 
uvicorn.run(app)
