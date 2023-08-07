window.onload = () => {
  let data = [];

  const gernerateUUID = () => {
    return Math.floor(Math.random() * 100000001).toString();
  };

  const drawCard = () => {
    const card = document.createElement("div");
    card.classList.add("card");

    const input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("placeholder", "Enter your task");
    card.prepend(input);

    const btnList = document.createElement("div");

    const modifyBtn = document.createElement("button");
    modifyBtn.innerText = "수정";
    btnList.prepend(modifyBtn);

    const deleteBtn = document.createElement("button");
    deleteBtn.innerText = "삭제";
    btnList.prepend(deleteBtn);

    card.appendChild(btnList);

    return card;
  };

  const addTodoCard = (e) => {
    const todo = document.getElementById("todo");
    todo.prepend(drawCard());

    const initData = {
      id: gernerateUUID(),
      title: "",
      status: "TODO",
      startAt: "",
      endAt: "",
    };
    data.push(initData);
  };

  const addInProgressCard = (e) => {
    const todo = document.getElementById("inProgress");
    todo.prepend(drawCard());

    const initData = {
      id: gernerateUUID(),
      title: "",
      status: "IN_PROGRESS",
      startAt: "",
      endAt: "",
    };
    data.push(initData);
  };

  const addDoneCard = (e) => {
    const todo = document.getElementById("done");
    todo.prepend(drawCard());

    const initData = {
      id: gernerateUUID(),
      title: "",
      status: "DONE",
      startAt: "",
      endAt: "",
    };
    data.push(initData);
  };

  document.getElementById("addTodoBtn").addEventListener("click", addTodoCard);
  document
    .getElementById("addInProgressBtn")
    .addEventListener("click", addInProgressCard);
  document.getElementById("addDoneBtn").addEventListener("click", addDoneCard);
};
