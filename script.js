const initialTopics = [
    { id: 1, name: "Variables", category: "Java Fundamentals", difficulty: 1, importance: 5, completed: false },
    { id: 2, name: "Loops", category: "Java Fundamentals", difficulty: 2, importance: 5, completed: false },
    { id: 3, name: "Arrays", category: "Java Fundamentals", difficulty: 3, importance: 5, completed: false },
    { id: 4, name: "OOP Basics", category: "Java Fundamentals", difficulty: 3, importance: 4, completed: false },
    { id: 5, name: "Recursion", category: "DSA", difficulty: 4, importance: 5, completed: false },
    { id: 6, name: "Stack", category: "DSA", difficulty: 3, importance: 4, completed: false },
    { id: 7, name: "Queue", category: "DSA", difficulty: 3, importance: 4, completed: false },
    { id: 8, name: "Sorting", category: "DSA", difficulty: 4, importance: 5, completed: false },
    { id: 9, name: "Binary Search", category: "DSA", difficulty: 4, importance: 5, completed: false },
    { id: 10, name: "Graphs", category: "DSA", difficulty: 5, importance: 5, completed: false }
];

const graph = {
    "Variables": ["Loops"],
    "Loops": ["Arrays"],
    "Arrays": ["Sorting", "Binary Search"],
    "OOP Basics": ["Stack", "Queue"],
    "Queue": ["Graphs"],
    "Recursion": ["Graphs"],
    "Sorting": [],
    "Binary Search": [],
    "Stack": [],
    "Graphs": []
};

let topics = initialTopics.map(topic => ({ ...topic }));
let nextId = 11;
let undoStack = [];
let studyQueue = [];
let highlightedId = null;

const topicList = document.getElementById("topicList");
const queueList = document.getElementById("queueList");
const completedCount = document.getElementById("completedCount");
const searchResult = document.getElementById("searchResult");
const pathResult = document.getElementById("pathResult");

document.getElementById("addTopicBtn").addEventListener("click", addTopic);
document.getElementById("priorityBtn").addEventListener("click", sortByPriority);
document.getElementById("undoBtn").addEventListener("click", undoCompletion);
document.getElementById("searchBtn").addEventListener("click", searchTopic);
document.getElementById("studyNextBtn").addEventListener("click", studyNext);
document.getElementById("pathBtn").addEventListener("click", showPath);
document.getElementById("resetBtn").addEventListener("click", resetApp);

function priorityScore(topic) {
    return topic.importance * 2 + topic.difficulty;
}

function render() {
    topicList.innerHTML = "";

    topics.forEach(topic => {
        const card = document.createElement("article");
        card.className = `topic-card ${topic.completed ? "completed" : ""} ${topic.id === highlightedId ? "highlight" : ""}`;

        card.innerHTML = `
            <div class="topic-title">
                <h3>${topic.name}</h3>
                <span class="status">${topic.completed ? "Completed" : "Pending"}</span>
            </div>
            <div>${topic.category}</div>
            <div class="meta-grid">
                <div class="meta"><small>Difficulty</small><strong>${topic.difficulty}</strong></div>
                <div class="meta"><small>Importance</small><strong>${topic.importance}</strong></div>
                <div class="meta"><small>Priority</small><strong>${priorityScore(topic)}</strong></div>
            </div>
            <div class="card-actions">
                <button data-action="complete" data-id="${topic.id}">Complete</button>
                <button data-action="queue" data-id="${topic.id}">Queue</button>
            </div>
        `;

        topicList.appendChild(card);
    });

    topicList.querySelectorAll("button").forEach(button => {
        button.addEventListener("click", handleTopicAction);
    });

    renderQueue();
    completedCount.textContent = topics.filter(topic => topic.completed).length;
}

function renderQueue() {
    queueList.innerHTML = "";

    if (studyQueue.length === 0) {
        queueList.innerHTML = `<p class="empty-state">No topics queued yet.</p>`;
        return;
    }

    studyQueue.forEach((id, index) => {
        const topic = findById(id);
        const item = document.createElement("div");
        item.className = "queue-item";
        item.textContent = `${index + 1}. ${topic.name}`;
        queueList.appendChild(item);
    });
}

function handleTopicAction(event) {
    const id = Number(event.target.dataset.id);
    const action = event.target.dataset.action;

    if (action === "complete") {
        completeTopic(id);
    }

    if (action === "queue") {
        studyQueue.push(id);
        render();
    }
}

function completeTopic(id) {
    const topic = findById(id);

    if (!topic || topic.completed) {
        return;
    }

    topic.completed = true;
    undoStack.push(id);
    highlightedId = id;
    render();
}

function undoCompletion() {
    const id = undoStack.pop();

    if (!id) {
        searchResult.textContent = "Nothing to undo.";
        return;
    }

    const topic = findById(id);
    topic.completed = false;
    highlightedId = id;
    render();
}

function addTopic() {
    const nameInput = document.getElementById("topicName");
    const category = document.getElementById("topicCategory").value;
    const difficulty = clampScore(document.getElementById("difficulty").value);
    const importance = clampScore(document.getElementById("importance").value);
    const name = nameInput.value.trim();

    if (name.length === 0) {
        nameInput.focus();
        return;
    }

    topics.push({
        id: nextId,
        name,
        category,
        difficulty,
        importance,
        completed: false
    });

    graph[name] = graph[name] || [];
    highlightedId = nextId;
    nextId++;
    nameInput.value = "";
    render();
}

function sortByPriority() {
    topics = mergeSort(topics);
    highlightedId = null;
    render();
}

function mergeSort(items) {
    if (items.length <= 1) {
        return items;
    }

    const middle = Math.floor(items.length / 2);
    const left = mergeSort(items.slice(0, middle));
    const right = mergeSort(items.slice(middle));

    return merge(left, right);
}

function merge(left, right) {
    const result = [];
    let i = 0;
    let j = 0;

    while (i < left.length && j < right.length) {
        if (priorityScore(left[i]) >= priorityScore(right[j])) {
            result.push(left[i]);
            i++;
        } else {
            result.push(right[j]);
            j++;
        }
    }

    return result.concat(left.slice(i)).concat(right.slice(j));
}

function searchTopic() {
    const target = document.getElementById("searchInput").value.trim();
    const found = binarySearchByName(target);

    if (!found) {
        highlightedId = null;
        searchResult.textContent = "Topic not found.";
        render();
        return;
    }

    highlightedId = found.id;
    searchResult.textContent = `Found: ${found.name}, priority score ${priorityScore(found)}.`;
    render();
}

function binarySearchByName(target) {
    const sorted = [...topics].sort((a, b) => a.name.localeCompare(b.name));
    let left = 0;
    let right = sorted.length - 1;

    while (left <= right) {
        const middle = Math.floor((left + right) / 2);
        const current = sorted[middle];
        const result = current.name.localeCompare(target, undefined, { sensitivity: "accent" });

        if (result === 0) {
            return current;
        }

        if (result < 0) {
            left = middle + 1;
        } else {
            right = middle - 1;
        }
    }

    return null;
}

function studyNext() {
    const id = studyQueue.shift();

    if (!id) {
        searchResult.textContent = "Study queue is empty.";
        render();
        return;
    }

    completeTopic(id);
}

function showPath() {
    const start = document.getElementById("startTopic").value.trim();
    const end = document.getElementById("endTopic").value.trim();
    const path = bfsPath(start, end);

    pathResult.innerHTML = "";

    if (path.length === 0) {
        pathResult.innerHTML = `<p class="empty-state">No path found.</p>`;
        return;
    }

    path.forEach((step, index) => {
        if (index > 0) {
            const arrow = document.createElement("span");
            arrow.className = "path-arrow";
            arrow.textContent = "->";
            pathResult.appendChild(arrow);
        }

        const node = document.createElement("span");
        node.className = "path-step";
        node.textContent = step;
        pathResult.appendChild(node);
    });
}

function bfsPath(start, end) {
    if (!graph[start] || !graph[end]) {
        return [];
    }

    const queue = [start];
    const visited = new Set([start]);
    const parent = {};

    while (queue.length > 0) {
        const current = queue.shift();

        if (current === end) {
            return buildPath(parent, start, end);
        }

        graph[current].forEach(neighbour => {
            if (!visited.has(neighbour)) {
                visited.add(neighbour);
                parent[neighbour] = current;
                queue.push(neighbour);
            }
        });
    }

    return [];
}

function buildPath(parent, start, end) {
    const path = [];
    let current = end;

    while (current) {
        path.unshift(current);

        if (current === start) {
            break;
        }

        current = parent[current];
    }

    return path;
}

function resetApp() {
    topics = initialTopics.map(topic => ({ ...topic }));
    nextId = 11;
    undoStack = [];
    studyQueue = [];
    highlightedId = null;
    searchResult.textContent = "";
    pathResult.innerHTML = "";
    render();
}

function findById(id) {
    return topics.find(topic => topic.id === id);
}

function clampScore(value) {
    const number = Number(value);

    if (number < 1) {
        return 1;
    }

    if (number > 5) {
        return 5;
    }

    return number;
}

render();
