from pathlib import Path
import os, subprocess, shutil, json

root = Path(__file__).resolve().parents[1]
os.chdir(root)
saida = root / "build/classes"
saida.mkdir(parents=True, exist_ok=True)
fontes = sorted(Path("src").rglob("*.java")) + sorted(Path("tests").rglob("*.java"))
subprocess.run(
    [
        os.environ.get("JAVAC", "javac"),
        "-encoding",
        "UTF-8",
        "-d",
        str(saida),
        *[str(p) for p in fontes],
    ],
    check=True,
)
teste = (
    "AlgoritmosTest" if (Path("tests") / "AlgoritmosTest.java").exists() else "PooTest"
)
result = subprocess.run(
    [os.environ.get("JAVA", "java"), "-cp", str(saida), teste],
    capture_output=True,
    text=True,
    check=True,
    timeout=120,
)
print(result.stdout, end="")
Path("build/testes.log").write_text(result.stdout, encoding="utf-8")
Path("build/validacao.json").write_text(
    json.dumps(
        {
            "modo": "Java compilado; testes reais",
            "classe": teste,
            "passou": True,
            "saida": result.stdout,
        },
        ensure_ascii=False,
        indent=2,
    )
    + "\n",
    encoding="utf-8",
)
